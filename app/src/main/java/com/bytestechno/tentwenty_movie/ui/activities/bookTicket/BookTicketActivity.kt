package com.bytestechno.tentwenty_movie.ui.activities.bookTicket

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bytestechno.tentwenty_movie.R
import com.bytestechno.tentwenty_movie.data.models.tickets.Tickets
import com.bytestechno.tentwenty_movie.databinding.ActivityBookTicketBinding
import com.bytestechno.tentwenty_movie.databinding.SeatInfoBinding
import java.lang.NumberFormatException
import java.util.*

class BookTicketActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityBookTicketBinding
    private lateinit var viewModel: BookTicketVM
    private lateinit var data: Tickets
    private var movieName = ""
    private var releaseDate = ""
    private var movieId = 0
    private var locationId = -1;
    private var cinemaId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_ticket)
        viewModel = ViewModelProvider(this).get(BookTicketVM::class.java)
        movieId = intent.extras?.get("id") as Int
        movieName = intent.extras?.get("name") as String
        releaseDate = intent.extras?.get("date") as String

        /*viewModel.ticket.observe(this, androidx.lifecycle.Observer {
            if (it != 0.toLong()){
                confirmSeatScreen()
            }
        })*/

        binding.location.setOnClickListener(this)
        binding.cinema.setOnClickListener(this)
        binding.btnReserve.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.location -> {
                showOptionDialog(resources.getStringArray(R.array.location), locationId, true)
            }
            R.id.cinema -> {
                showOptionDialog(resources.getStringArray(R.array.cinema), cinemaId, false)
            }
            R.id.btn_reserve -> {
                if (locationId == -1) {
                    Toast.makeText(this, "Select Location", Toast.LENGTH_SHORT).show()
                    return
                }

                if (cinemaId == -1) {
                    Toast.makeText(this, "Select Cinema", Toast.LENGTH_SHORT).show()
                    return
                }

                if (binding.seatNum.text.trim().isEmpty()) {
                    Toast.makeText(this, "Enter Seat Number", Toast.LENGTH_SHORT).show()
                    return
                } else {
                    try {
                        Integer.parseInt(binding.seatNum.text.toString())
                    } catch (nfe: NumberFormatException) {
                        Toast.makeText(this, "Enter only Number", Toast.LENGTH_SHORT).show()
                        return
                    }
                }

                //insert Seat Number
                data = Tickets(
                    movieId,
                    movieName,
                    releaseDate,
                    locationId,
                    cinemaId,
                    Integer.parseInt(binding.seatNum.text.toString()),
                    23455
                )
                viewModel.insertTicket(data)
                confirmSeatScreen()
            }
        }
    }

    private fun showOptionDialog(names: Array<String>, checkItem: Int, isLocation: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose item")
        builder.setSingleChoiceItems(names, checkItem) { dialog, which ->
            if (isLocation) {
                binding.txtLoc.text = names[which]
                locationId = which

            } else {
                binding.txtCinema.text = names[which]
                cinemaId = which
            }
            dialog.dismiss()
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }

    private fun confirmSeatScreen() {
        val builder = AlertDialog.Builder(this, R.style.fullScreenDialogTheme)
        val dialog = builder.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding: SeatInfoBinding = SeatInfoBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)

        dialogBinding.btnBack.setOnClickListener { dialog.dismiss() }
        dialogBinding.seatDetail = data
        dialogBinding.location = resources.getStringArray(R.array.location)[data.locationId]
        dialogBinding.cinema = resources.getStringArray(R.array.cinema)[data.cinemaId]

        dialog.setCancelable(true)
        dialog.show()
    }

}