package xyz.beathub.beathub_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.beathub.beathub_android.databinding.ItemCommitBinding
import xyz.beathub.beathub_android.models.Commit

class CommitAdapter(
    private var items: List<Commit>,
    private val onItemClickCallback: (Commit) -> Unit
) : RecyclerView.Adapter<CommitAdapter.CommitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {

        val binding = ItemCommitBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CommitViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(commits: List<Commit>) {
        //val diffResult = DiffUtil.calculateDiff(ReviewDiff(items, reviews))
        items = commits
        //diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    fun addItem(commit: Commit) {
        items = listOf(commit)+items
        notifyItemInserted(0)
    }

    inner class CommitViewHolder(private val binding: ItemCommitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Commit) {
            binding.commitName.text = "Commit: "+item.message
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
            binding.root.setOnClickListener {
                onItemClickCallback(item)
            }

        }
    }
}