package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val employeeDao = (application as EmployeeApp).db.employeeDao()
        binding?.btnAdd?.setOnClickListener{
            addRecord(employeeDao)
        }

        lifecycleScope.launch {
            employeeDao.fetchAllEmployees().collect(){
                val list = ArrayList(it)
                setUpListDataIntoRecyclerViewer(list,employeeDao)
            }
        }
    }

    private fun addRecord(employeeDao: EmployeeDao){
        val name = binding?.etName?.text.toString()
        val email = binding?.etEmailId?.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty()){
            lifecycleScope.launch{ employeeDao.insert(EmployeeEntity(name=name, email =email))
                Toast.makeText(applicationContext, "Record Saved", Toast.LENGTH_LONG).show()
                binding?.etName?.text?.clear()
                binding?.etEmailId?.text?.clear()
            }
        }else{
            Toast.makeText(applicationContext, "Enter Name and Email!", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpListDataIntoRecyclerViewer
                (employeesList:ArrayList<EmployeeEntity>,
                 employeeDao: EmployeeDao){
        if (employeesList.isNotEmpty()){
            val itemAdaptor = ItemAdaptor(employeesList)

            binding?.rvItemsList?.layoutManager= LinearLayoutManager(this)
            binding?.rvItemsList?.adapter = itemAdaptor
            binding?.rvItemsList?.visibility = View.VISIBLE
            binding?.tvNoRecordsAvailable?.visibility = View.GONE
        }
        else{
            binding?.rvItemsList?.visibility = View.GONE
            binding?.tvNoRecordsAvailable?.visibility= View.VISIBLE
        }
    }
}