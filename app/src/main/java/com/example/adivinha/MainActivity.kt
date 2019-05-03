package com.example.adivinha

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.modal_chute.*
import kotlinx.android.synthetic.main.modal_dificuldade.*
import kotlinx.android.synthetic.main.modal_vitoria.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var adivinha : Int? = null
    var dificuldade : Int? = null
    var turno : Int? = null
    var chute : Int? = null
    var qtd_chutes : Int? = null
    var dialog_dif : Dialog? = null
    var chute_dialog : Dialog? = null
    var vit_dialog : Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rand = Random(Math.random().toLong())

        startVars(rand)

        if(dialog_dif == null)
            dialog_dif = Dialog(this)
        dialog_dif!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_dif!!.setContentView(R.layout.modal_dificuldade)

        btn_d_facil.setOnClickListener{
            this.dificuldade = 30
            this.adivinha = rand.nextInt(30)
            dialog_dif!!.dismiss()
        }

        btn_d_normal.setOnClickListener {
            this.dificuldade = 60
            this.adivinha = rand.nextInt(60)
            dialog_dif!!.dismiss()
        }

        btn_d_dificil.setOnClickListener {
            this.dificuldade = 120
            this.adivinha = rand.nextInt(120)
            dialog_dif!!.dismiss()
        }

        dialog_dif!!.show()

        btn_chutar.setOnClickListener {
            if(chute_dialog == null)
                chute_dialog = Dialog(this)
            chute_dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            chute_dialog!!.setContentView(R.layout.modal_chute)

            txt_c_titulo!!.text = "Jogador " + this.turno + ", faça um chute!"

            btn_c_ok.setOnClickListener{
                if(!edt_c_chute.text.toString().isNullOrEmpty()) {
                    val c = edt_c_chute.text.toString().toInt()
                    if(c >= 0 && c < this.dificuldade!!) {

                        txt_chute!!.text = c.toString()
                        this.qtd_chutes = this.qtd_chutes!! + 1
                        this.chute = c

                        chute_dialog!!.dismiss()

                        if(this.chute == this.adivinha){

                            if(vit_dialog == null)
                                vit_dialog = Dialog(this)
                            vit_dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                            vit_dialog!!.setContentView(R.layout.modal_vitoria)

                            txt_v_titulo!!.text = "O jogador " + this.turno + " venceu chutando "+ this.qtd_chutes +" vezes! Parabéns!! "

                            vit_dialog!!.setOnDismissListener {
                                startVars(rand)
                                if(dialog_dif == null)
                                    dialog_dif = Dialog(this)
                                dialog_dif!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                                dialog_dif!!.setContentView(R.layout.modal_dificuldade)
                                dialog_dif!!.show()
                            }

                            vit_dialog!!.show()

                        } else {

                            if(this.chute!! > this.adivinha!!){
                                Toast.makeText(this, "Você fez um chute ALTO!", Toast.LENGTH_SHORT)
                            } else {
                                Toast.makeText(this, "Você fez um chute BAIXO!", Toast.LENGTH_SHORT)
                            }

                            if (this.turno == 1) {
                                this.turno = 2
                            } else {
                                this.turno = 1
                            }
                        }
                    } else {
                        Toast.makeText(this, "Chute um número entre 0 e " + this.dificuldade, Toast.LENGTH_SHORT)
                    }
                } else {
                    Toast.makeText(this, "Digite um número!", Toast.LENGTH_SHORT)
                }
            }

            chute_dialog!!.show()

        }

    }

    private fun startVars(rand: Random) {
        this.dificuldade = 60
        this.adivinha = rand.nextInt(60)
        this.turno = 1
        this.chute = 0
        txt_chute!!.text = "0"
        this.qtd_chutes = 0
    }
}
