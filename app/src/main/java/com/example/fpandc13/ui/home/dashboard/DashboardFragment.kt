package com.example.fpandc13.ui.home.dashboard


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fpandc13.R
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.databinding.FragmentDashboardBinding
import com.example.fpandc13.ui.home.DatePickerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment: Fragment(R.layout.fragment_dashboard) {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    var spinner: Spinner? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val spinner = v.findViewById<View>(R.id.airport) as Spinner
        val adapter = ArrayAdapter(
            this.requireActivity()!!, android.R.layout.simple_spinner_dropdown_item, bandara
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        return v
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)


//        spinner = this.spinner
//        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
//        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, bandara)
//        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, bandara)
        // Set layout to use when the list of choices appear
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        // Set Adapter to Spinner
//        spinner!!.setAdapter(aa)

        binding.apply {
            dateArEdit.setOnClickListener {
                // create new instance of DatePickerFragment
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                // we have to implement setFragmentResultListener
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        dateArEdit.text = date
                    }
                }

                // show
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }



        binding.SearchButton.setOnClickListener {
            val airportName: String = binding.airport.getSelectedItem().toString()
            val Departure: String = binding.dateDepEdit.getText().toString()
            val Arrival: String = binding.dateDeparture.getText().toString()
            val price: Int = 0
            val kelas : String = ""
            val airportloc : String = ""

            val searchTickets =
                parseFormIntoEntity(airportName,airportloc,Departure,Arrival,price, kelas)
            navigateToSearchResult(searchTickets)
        }

        binding.apply {
            dateDepEdit.setOnClickListener {
                // create new instance of DatePickerFragment
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                // we have to implement setFragmentResultListener
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        dateDepEdit.text = date
                    }
                }

                // show
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseFormIntoEntity(
        airport : String,
        airportloc : String,
        departure_date: String,
        arrival_date: String,
        price : Int,
        kelas : String


        ): SearchTicketRequestBody {
        return SearchTicketRequestBody(
            airportloc,
            airport,
            arrival_date,
            kelas,
            departure_date,
            price


        )
    }

    private fun navigateToSearchResult(searchTickets: SearchTicketRequestBody) {
        val action = DashboardFragmentDirections.actionFirstFragmentToHiltSearchResultFragment(searchTickets)
        Log.d("args", searchTickets.toString())
        findNavController().navigate(action)
    }

    var bandara = arrayOf(
        "Choose an Airport",
        "Pattimura International Airport",
        "Raja Haji Fisabilillah International Airport",
        "Radin Inten II International Airport",
        "Halim Perdana Kusuma International Airport",
        "Minangkabau International Airport",
        "Sultan Mahmud Badaruddin II International Airport",
        "Abdul Rachman Saleh International Airport",
        "Blimbingsari International Airport",
        "Juwata International Airport",
        "Sentani International Airport",
        "Polonia International Airport",
        "El Tari International Airport",
        "Supadio International Airport",
        "Sultan Mahmud Badaruddin II International Airport",
        "Syamsudin Noor International Airport",
        "Pattimura International Airport",
        "Sultan Iskandar Muda International Airport",
        "Raja Haji Fisabilillah International Airport",
        "Lombok International Airport",
        "Halim Perdana Kusuma International Airport",
        "Hang Nadim International Airport",
        "Juanda International Airport",
        "Soekarno Hatta International Airport"
    )


}