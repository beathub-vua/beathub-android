package xyz.beathub.beathub_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.beathub.beathub_android.databinding.ItemRepoBinding
import xyz.beathub.beathub_android.models.Repo
import java.lang.Exception

class RepoAdapter(
    private var items: List<Repo>,
    private val onItemClickCallback: (Repo) -> Unit
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {

        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(repos: List<Repo>) {
        //val diffResult = DiffUtil.calculateDiff(ReviewDiff(items, reviews))
        items = repos
        //diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    fun addItem(repo: Repo) {
        items = listOf(repo)+items
        notifyItemInserted(0)
    }

    inner class RepoViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repo) {
            binding.repoName.text = item.name
//            binding.reviewContent.isVisible = !item.comment.isNullOrEmpty()
//            binding.reviewContent.text = item.comment
//            binding.reviewerName.text = item.user.email.getUsername()
//
////            binding.frameLayout.setBackgroundColor( if (item.sync) Color.parseColor("#ffffff") else Color.parseColor("#808080"))
//            if (!item.user.imageUrl.isNullOrEmpty()){
//                Glide.with(itemView.context).load(GlideUrlCustomCacheKey(item.user.imageUrl)).into(binding.profileIconImage)
//            }else{
//                binding.profileIconImage.setImageResource(R.drawable.ic_painting_art)
//            }

        }
    }
}