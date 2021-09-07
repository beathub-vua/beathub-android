package xyz.beathub.beathub_android.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.beathub.beathub_android.USER_NAME_KEY
import xyz.beathub.beathub_android.adapters.CommitAdapter
import xyz.beathub.beathub_android.adapters.RepoAdapter
import xyz.beathub.beathub_android.backgroundThreadShortToast
import xyz.beathub.beathub_android.databinding.FragmentCommitBinding
import xyz.beathub.beathub_android.databinding.FragmentRepoBinding
import xyz.beathub.beathub_android.models.Commit
import xyz.beathub.beathub_android.models.Repo
import xyz.beathub.beathub_android.modules.ApiModule


class CommitFragment : Fragment() {

    private var _binding: FragmentCommitBinding? = null

    private var commitsAdapter: CommitAdapter? = null

    private val binding get() = _binding!!

    private val commitResultLiveData: MutableLiveData<List<Commit>> by lazy { MutableLiveData<List<Commit>>() }

    private val args: CommitFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecycler()

        binding.titleText.text = "${args.title}"
        binding.descriptionText.text = "${args.description}"
        commitResultLiveData.observe(this.viewLifecycleOwner) {
            commitsAdapter?.setItems(it)
        }

        ApiModule.retrofit.getCommits(
            args.commitId.toInt()
        ).enqueue(object :
            Callback<List<Commit>> {
            override fun onResponse(
                call: Call<List<Commit>>,
                response: Response<List<Commit>>
            ) {
                val commits = response.body()
                if (commits != null) {
                    commitResultLiveData.postValue(commits)
                }
            }
            override fun onFailure(call: Call<List<Commit>>, t: Throwable) {
                backgroundThreadShortToast(requireActivity(), "Error!")
            }
        })

    }

    private fun initToolbar() {
        val username = requireActivity().getPreferences(Context.MODE_PRIVATE).getString(
            USER_NAME_KEY, "beathub"
        )

//        binding.logoutImage.setOnClickListener {
//            RepoFragmentDirections.actionLogout().let {
//                findNavController().navigate(it)
//            }
//        }
//        binding.optionsImage.setOnClickListener {
//            RepoFragmentDirections.actionOptions().let {
//                findNavController().navigate(it)
//            }
//        }
    }

    private fun initRecycler() {
        commitsAdapter = CommitAdapter(emptyList()) { item ->

        }.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.reposRecyclerView.adapter = commitsAdapter


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}