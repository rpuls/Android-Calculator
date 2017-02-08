package com.example.rasmus.calculator

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import kotlin.concurrent.thread

class MainActivity : Activity() {

    var lastNumber: Double = 0.0
    var lastOp: String = ""
    var isResult: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clear()
        setDigitBtn(b0,"0")
        setDigitBtn(b1,"1")
        setDigitBtn(b2,"2")
        setDigitBtn(b3,"3")
        setDigitBtn(b4,"4")
        setDigitBtn(b5,"5")
        setDigitBtn(b6,"6")
        setDigitBtn(b7,"7")
        setDigitBtn(b8,"8")
        setDigitBtn(b9,"9")
        setOperatorBtn(bDiv,"/")
        setOperatorBtn(bMult,"*")
        setOperatorBtn(bPlus,"+")
        setOperatorBtn(bMin,"-")
        setOperatorBtn(bEq,"")
        bDot.onClick {
            onNumber(".")
            bDot.enabled = false
        }
        bC.onClick {
            clear()
            log("C")
        }
    }

    fun setDigitBtn(btn: Button, d: String){
        btn.onClick{
            onNumber(d)
        }
    }

    fun setOperatorBtn(btn: Button, op: String){
        btn.onClick{
            onOperator(op)
        }
    }

    fun onNumber(d: String){
        if(isResult){
            twResult.text = ""
            isResult = false
        }
        twResult.append(d)
        log(d)
    }

    fun onOperator(op: String){
        if(isResult){
            lastOp = op
        }else{
            val currentNumber = twResult.text.toString().toDouble()
            when (lastOp){
                "+" -> lastNumber += currentNumber
                "-" -> lastNumber -= currentNumber
                "*" -> lastNumber *= currentNumber
                "/" -> lastNumber /= currentNumber
                "" -> lastNumber = currentNumber
            }
            bDot.enabled = true
            lastOp = op
            twResult.text = lastNumber.toString()
            isResult = true
        }
        log(op)
    }

    fun log(lp: String){
        twLastPress.text = lp
        twLastNum.text = lastNumber.toString()
        twLastOp.text = lastOp
    }

    fun clear(){
        lastNumber = 0.0
        lastOp = ""
        twResult.text = ""
        bDot.enabled = true
        log("")
    }

}
