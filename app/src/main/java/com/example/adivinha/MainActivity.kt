package com.example.adivinha

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.modal_chute.*
import kotlinx.android.synthetic.main.modal_dificuldade.*
import kotlinx.android.synthetic.main.modal_vitoria.*
import java.util.*

class MainActivity : Activity() {

    var adivinha : Int? = null
    var dificuldade : Int? = null
    var turno : Int? = null
    var chute : Int? = null
    var qtd_chutes : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rand = Random(Math.random().toLong())

        startVars(rand)


        val dialog_dif = Dialog(this)
        dialog_dif!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_dif!!.setContentView(R.layout.modal_dificuldade)

        dialog_dif!!.btn_d_facil.setOnClickListener{
            this.dificuldade = 30
            this.adivinha = rand.nextInt(30)
            dialog_dif!!.dismiss()
        }

        dialog_dif!!.btn_d_normal.setOnClickListener {
            this.dificuldade = 60
            this.adivinha = rand.nextInt(60)
            dialog_dif!!.dismiss()
        }

        dialog_dif!!.btn_d_dificil.setOnClickListener {
            this.dificuldade = 120
            this.adivinha = rand.nextInt(120)
            dialog_dif!!.dismiss()
        }

        dialog_dif!!.show()

        btn_chutar.setOnClickListener {

            val chute_dialog = Dialog(this)
            chute_dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            chute_dialog!!.setContentView(R.layout.modal_chute)

            chute_dialog!!.txt_c_titulo!!.text = "Jogador " + this.turno + ", faça um chute!"

            chute_dialog!!.btn_c_ok.setOnClickListener{
                if(!chute_dialog!!.edt_c_chute.text.toString().isNullOrEmpty()) {
                    val c = chute_dialog!!.edt_c_chute.text.toString().toInt()
                    if(c >= 0 && c < this.dificuldade!!) {

                        txt_chute!!.text = c.toString()
                        this.qtd_chutes = this.qtd_chutes!! + 1
                        this.chute = c

                        chute_dialog!!.dismiss()

                        if(this.chute == this.adivinha){


                            val vit_dialog = Dialog(this)
                            vit_dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                            vit_dialog!!.setContentView(R.layout.modal_vitoria)

                            vit_dialog!!.txt_v_titulo!!.text = "O jogador " + this.turno + " venceu chutando "+ this.qtd_chutes +" vezes! Parabéns!! "

                            vit_dialog!!.setOnDismissListener {
                                startVars(rand)

                                val dialog_dif = Dialog(this)
                                dialog_dif!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                                dialog_dif!!.setContentView(R.layout.modal_dificuldade)

                                dialog_dif!!.btn_d_facil.setOnClickListener{
                                    this.dificuldade = 30
                                    this.adivinha = rand.nextInt(30)
                                    dialog_dif!!.dismiss()
                                }

                                dialog_dif!!.btn_d_normal.setOnClickListener {
                                    this.dificuldade = 60
                                    this.adivinha = rand.nextInt(60)
                                    dialog_dif!!.dismiss()
                                }

                                dialog_dif!!.btn_d_dificil.setOnClickListener {
                                    this.dificuldade = 120
                                    this.adivinha = rand.nextInt(120)
                                    dialog_dif!!.dismiss()
                                }

                                dialog_dif!!.show()
                            }

                            vit_dialog!!.show()

                        } else {

                            if(this.chute!! > this.adivinha!!){
                                Toast.makeText(this, "Você fez um chute ALTO!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Você fez um chute BAIXO!", Toast.LENGTH_SHORT).show()
                            }

                            if (this.turno == 1) {
                                this.turno = 2
                            } else {
                                this.turno = 1
                            }
                        }
                    } else {
                        Toast.makeText(this, "Chute um número entre 0 e " + this.dificuldade, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Digite um número!", Toast.LENGTH_SHORT).show()
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
