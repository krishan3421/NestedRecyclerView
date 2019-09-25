package com.roomdatabase.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["empId"], unique = true),Index(value = ["mobileNo"], unique = true)])
data class Employee(
                    @PrimaryKey(autoGenerate = true)
                    var id:Int =0,
                    var empId:String? = "",
                    var name:String? ="",
                    var mobileNo:String? ="",
                    var designation:String?=""
)