package xyz.beathub.beathub_android.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.beathub.beathub_android.USER_ID_KEY
import xyz.beathub.beathub_android.USER_NAME_KEY
import xyz.beathub.beathub_android.adapters.RepoAdapter
import xyz.beathub.beathub_android.backgroundThreadShortToast
import xyz.beathub.beathub_android.databinding.FragmentRepoBinding
import xyz.beathub.beathub_android.models.Repo
import xyz.beathub.beathub_android.modules.ApiModule


class RepoFragment : Fragment() {

    private var _binding: FragmentRepoBinding? = null

    private var reposAdapter: RepoAdapter? = null

    private val binding get() = _binding!!

    private val repoResultLiveData: MutableLiveData<List<Repo>> by lazy { MutableLiveData<List<Repo>>() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRepoBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecycler()
        repoResultLiveData.observe(this.viewLifecycleOwner) {
            reposAdapter?.setItems(it)
        }

        ApiModule.retrofit.getProjects(
            requireActivity().getPreferences(Context.MODE_PRIVATE).getString(
                USER_ID_KEY, "-1"
            )
                .orEmpty()
        ).enqueue(object :
            Callback<List<Repo>> {
            override fun onResponse(
                call: Call<List<Repo>>,
                response: Response<List<Repo>>
            ) {
                val repos = response.body()
                if (repos != null) {
                    repoResultLiveData.postValue(repos)
                }
            }
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                backgroundThreadShortToast(requireActivity(), "Error!")
            }
        })

    }

    private fun initToolbar() {
        val username = requireActivity().getPreferences(Context.MODE_PRIVATE).getString(
            USER_NAME_KEY, "beathub"
        )
        binding.textView.text = "Welcome, $username"

        binding.logoutImage.setOnClickListener {
            RepoFragmentDirections.actionLogout().let {
                findNavController().navigate(it)
            }
        }
        binding.optionsImage.setOnClickListener {
            RepoFragmentDirections.actionOptions().let {
                findNavController().navigate(it)
            }
        }
    }

    private fun initRecycler() {
        reposAdapter = RepoAdapter(emptyList()) { item ->
            RepoFragmentDirections.actionRepoToCommit(
                item.repoId.toString(),
                item.name,
                item.description
            )
                .let {
                    findNavController().navigate(it)
                }
        }.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.reposRecyclerView.adapter = reposAdapter


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}