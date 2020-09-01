package uz.jagito.bookstore.ui.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.intro_login_page_item.view.*
import kotlinx.android.synthetic.main.intro_page_item.view.*
import uz.jagito.bookstore.R
import uz.jagito.bookstore.models.IntroModel
import uz.jagito.bookstore.utils.extensions.bindItem
import uz.jagito.bookstore.utils.extensions.inflate
import uz.jagito.bookstore.utils.showToast

class IntroAdapter(private val data: List<IntroModel>) :
    RecyclerView.Adapter<IntroAdapter.ViewHolder>() {
    private val introPage:Int = 0
    private val loginPage:Int = 1
    private var onSignIn: EmptyBlock? = null
    private var onSignUp: EmptyBlock? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("testtt", "viewType = $viewType")
        return ViewHolder(
            parent.inflate(
                if (viewType == introPage)
                    R.layout.intro_page_item
                else
                    R.layout.intro_login_page_item
            ), viewType
        )
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == data.size ) loginPage else introPage
    }

    override fun getItemCount(): Int = data.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(view: View,private val viewType:Int) : RecyclerView.ViewHolder(view) {
        init {
                Log.d("testtt", "1 - viewHolder |viewType = $viewType")
            view.apply {
                if (viewType == loginPage){
                    Log.d("testtt", "2")
                    sign_in.setOnClickListener {
                        Log.d("testtt", "3")
                        onSignIn?.invoke()
                    }
                    sign_up.setOnClickListener {

                        onSignUp?.invoke()
                    }
                }
            }
        }

        fun bind() = bindItem {
            Log.d("testtt", "2 - bindItem |viewType = $itemViewType")
            if (viewType == introPage) {
                val d = data[adapterPosition]
                intro_item_title.text = d.title
                intro_item_description.text = d.description
                intro_item_image.setImageResource(d.image)
            }
        }
    }

    fun onSignIn(f: EmptyBlock){
        onSignIn = f
    }

    fun onSignUp(f: EmptyBlock){
        onSignUp = f
    }
}
typealias EmptyBlock = (() -> Unit)