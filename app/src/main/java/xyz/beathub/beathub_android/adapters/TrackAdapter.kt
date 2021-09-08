package xyz.beathub.beathub_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.beathub.beathub_android.databinding.ItemTrackBinding
import xyz.beathub.beathub_android.models.Track

class TrackAdapter(
    private var items: List<Track>,
    private val onItemClickCallback: (Track) -> Unit
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {

        val binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TrackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(tracks: List<Track>) {
        //val diffResult = DiffUtil.calculateDiff(ReviewDiff(items, reviews))
        items = tracks
        //diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    fun addItem(track: Track) {
        items = listOf(track)+items
        notifyItemInserted(0)
    }

    inner class TrackViewHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Track) {
            binding.trackName.text = "Track: "+item.trackName
            binding.plugins.text = "Plugins: "+item.plugins.joinToString(", ") { it.name.toString() }
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