package com.example.testapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapp.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    private val args: GameWonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.next.setOnClickListener{
            val action = GameWonFragmentDirections.actionGameWonFragmentToGameFragment()
            findNavController().navigate(action)
        }
        Toast.makeText(context, "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}", Toast.LENGTH_SHORT).show()

        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.winner_menu, menu)

        if(getShareIntent().resolveActivity(requireActivity().packageManager) == null){
            menu.findItem(R.id.share).setVisible(false)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.share){
            shareSuccess()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getShareIntent(): Intent{
//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.type = "text/plain"
//        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
//
//        return shareIntent
        return ShareCompat.IntentBuilder.from(requireActivity()).setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions)).setType("text/plain").intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }
}