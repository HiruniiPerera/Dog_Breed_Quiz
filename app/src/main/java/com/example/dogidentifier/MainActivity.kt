package com.example.dogidentifier

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private var correctGuesses = 0
    private var incorrectGuesses = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        correctGuesses = 0
        incorrectGuesses = 0

        val dogmap = HashMap<String, List<Int>>()
        dogmap.put("American Bully", listOf (R.drawable.americanbully,R.drawable.americanbully_2,R.drawable.americanbully_3))
        dogmap.put("Boxer",listOf (R.drawable.boxer,R.drawable.boxer_2,R.drawable.boxer_3))
        dogmap.put("Cocker Spaniel",listOf (R.drawable.cockerspaniel,R.drawable.cockerspaniel_2,R.drawable.cockerspaniel_3))
        dogmap.put("Dachsund",listOf (R.drawable.dachsund,R.drawable.dachsund_2,R.drawable.dachsund_3))
        dogmap.put("Doberman",listOf (R.drawable.doberman,R.drawable.doberman_2,R.drawable.doberman_3))
        dogmap.put("German Sheperd",listOf (R.drawable.germansheperd,R.drawable.germansheperd_2,R.drawable.germansheperd_3))
        dogmap.put("Golden Retriever",listOf (R.drawable.goldenretriever,R.drawable.goldenretriever_2,R.drawable.goldenretriever_3))
        dogmap.put("Great Dane",listOf (R.drawable.greatdane,R.drawable.greatdane_2,R.drawable.greatdane_3))
        dogmap.put("Husky",listOf (R.drawable.husky,R.drawable.husky_2,R.drawable.husky_3))
        dogmap.put("Labrador",listOf (R.drawable.labrador,R.drawable.labrador_2,R.drawable.labrador_3))
        dogmap.put("Shih tzu",listOf (R.drawable.shitzu,R.drawable.shitzu_2,R.drawable.shitzu_3))
//        val myArray = Array(3) { arrayOf<Pair<String, Int>>() }
//
//        myArray[0] = arrayOf(Pair("AmericanBully",R.drawable.husky), Pair("AmericanBully",R.drawable.dachsund), Pair("AmericanBully",R.drawable.shitzu))

        val imageButton1 = findViewById<ImageButton>(R.id.imageButton1)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)
        val imageButton3 = findViewById<ImageButton>(R.id.imageButton3)

        val buttonlist = listOf<ImageButton>(imageButton1,imageButton2,imageButton3)

        shuffledogs(dogmap,buttonlist)

        val nextbutton = findViewById<Button>(R.id.nextbutton)
        nextbutton.setOnClickListener {
            shuffledogs(dogmap,buttonlist)
        }

        val finishbutton = findViewById<Button>(R.id.finishbutton)
        finishbutton.setOnClickListener {
            val intent = Intent(this, score_activity::class.java)
            intent.putExtra("correctGuesses", correctGuesses)
            intent.putExtra("incorrectGuesses", incorrectGuesses)
            startActivity(intent)
        }

    }

    private fun shuffledogs(dogmap : HashMap<String,List<Int>>, buttonlist:List<ImageButton>){

        val viewname = findViewById<TextView>(R.id.textView)
        viewname.setTextColor(Color.parseColor("#808080"))

        val selectedkeys = dogmap.keys.shuffled().take(3)

        val pickeddogs = Hashtable<String,List<Int>>()

        for (key in selectedkeys){
            pickeddogs[key]=dogmap[key]!!
        }
        val pickeddogslist = pickeddogs.values.toList()

        val imagesfinal = listOf<Int>(pickeddogslist[0].shuffled().first(),pickeddogslist[1].shuffled().first(),pickeddogslist[2].shuffled().first())


        imagesfinal.shuffled()
        buttonlist.shuffled()
        var i = 0
        for ((key,value )in pickeddogs){
            buttonlist[i].setImageResource(imagesfinal[i])
            buttonlist[i].contentDescription=key
            i++
        }
        viewname.text = pickeddogs.keys.toList().shuffled().first()

        checkDog(buttonlist,viewname)
        for (imgButt in buttonlist) {
            imgButt.isEnabled = true
            imgButt.isClickable = true
        }
    }
    private fun checkDog(dogButtList: List<ImageButton>, dogBreedText: TextView) {
        for (imgButt in dogButtList) {
            imgButt.setOnClickListener() {
                if (imgButt.contentDescription.equals(dogBreedText.text)) {
                    dogBreedText.text = "CORRECT!"
                    dogBreedText.setTextColor(Color.parseColor("#00FF00"))
                    correctGuesses++
                } else {
                    dogBreedText.text = "INCORRECT!"
                    dogBreedText.setTextColor(Color.parseColor("#FF0000"))
                    incorrectGuesses++
                }
                for (imgButt in dogButtList) {
                    imgButt.isEnabled = false
                    imgButt.isClickable = false
                }
            }
        }
    }


}