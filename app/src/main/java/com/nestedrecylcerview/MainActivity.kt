package com.nestedrecylcerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var headerAdapter: OrderAdapter
    private var list:MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        for(i in 0..20){
            list.add("$i")
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        headerAdapter = OrderAdapter(this@MainActivity,list)
        order_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = headerAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        headerAdapter.setOnItemClickListener(object :OrderAdapter.OrderClickListener{
            override fun onItemClick(position:Int, v: View) {
                Toast.makeText(this@MainActivity,"header $position",Toast.LENGTH_SHORT).show()
            }

        } )

        headerAdapter.setOnChildItemClickListener(object: OrderAdapter.OrderChildListener{
            override fun onChildItemClick(headerPosition: Int, childPosition: Int, headerView: View, childView: View) {
                Toast.makeText(this@MainActivity,"header $headerPosition,child $childPosition",Toast.LENGTH_SHORT).show()
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
