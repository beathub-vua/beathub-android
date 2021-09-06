package xyz.beathub.beathub_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.beathub.beathub_android.adapters.RepoAdapter
import xyz.beathub.beathub_android.databinding.FragmentRepoBinding
import xyz.beathub.beathub_android.util.REPOS

class RepoFragment : Fragment() {

    private var _binding: FragmentRepoBinding? = null

    private var reposAdapter: RepoAdapter? = null

    private val binding get() = _binding!!


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

    }

    private fun initToolbar() {
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
        reposAdapter = RepoAdapter(REPOS.toList()) { item ->
//            ShowsFragmentDirections.actionShowToDetails(
//                item.id
//            )
//                .let {
//                    findNavController().navigate(it)
//                    snackbar?.dismiss()
//                }
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