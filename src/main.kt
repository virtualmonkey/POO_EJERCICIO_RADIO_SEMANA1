package main


import main.radio.Radio


val Radio = Radio(

)

fun main(args: Array<String>) {
    do {
        println("""Bienvenido, El estado actual del radio es:
            $Radio
            Por Favor seleccione una opción:
            1. Encender Radio.
            2. Cambiar Frecuencia (AM/FM).
            3. Cambiar de estación.
            4. Subir o bajar Volúmen
            5. Apagar Radio.
            6. Salir del programa.

        """.trimMargin())
        println("Ingrese su selección: ")
        // variables generales
        var frecuencia = 0
        // hasta acá
        val strseleccion: String? = readLine()
        var seleccion = 0
        var okSeleccion = false
        if (strseleccion != null) {
            try {
                seleccion = strseleccion.toInt()
                okSeleccion = true

            } catch (error: NumberFormatException) {
                println("ERROR: Por favor ingrese un número")
                okSeleccion = false
            }
        }
        if (okSeleccion){
            seleccion = strseleccion!!.toInt()
        }

        if (seleccion==1){
            if (!Radio.isTurnedOn){
                Radio.turnOn()
                println("usted ha encendido el Radio")
            }
            else println("El radio ya está Encendido")
        }
        else if (seleccion==2){
            var okFrecuencia = false
            println("Seleccione el número de opción de frecuencia que desea sintonizar")
            println("1. AM")
            println("2. FM")

            val strfrecuencia: String? = readLine()
            if (strfrecuencia != null) {
                try {
                    frecuencia = strfrecuencia.toInt()
                    okFrecuencia = true

                } catch (error: NumberFormatException) {
                    println("ERROR: Por favor ingrese un número")
                    okFrecuencia= false
                }
            }
            if (okFrecuencia && 0<frecuencia && frecuencia<3){
                frecuencia = strfrecuencia!!.toInt()
                when (frecuencia){
                    1-> Radio.RangeToAM()
                    2->Radio.RangeToFM()
                }
            }

        }
        else if (seleccion==3){
            val frecActual = Radio.frecuencia
            var paso = 0.0
            var okPaso = false
            if (frecActual=="AM") {
                println("Ingrese el signo ´+´ o ´-´ para subir o bajar de estación")
                val strUpOrDown: String? = readLine()
                println("Ingrese el tamaño de salto entre estaciones que quiere dar (en decimales)")
                val strpaso: String? = readLine()

                if (strpaso != null) {
                    try {
                        paso = strpaso.toDouble()
                        okPaso = true

                    } catch (error: NumberFormatException) {
                        println("ERROR: Por favor ingrese un número en decimales")
                        okPaso = false
                    }

                }
                if (okPaso) {
                    if (strUpOrDown == "+") {
                        if ((Radio.Station + paso) <= 108.1) {

                            Radio.ChangeStation(frecActual, paso, "+")
                            println("La estación actual es: ${Radio.Station}")
                        } else {
                            println("La Frecuencia FM tiene una banda de alcance máximo de 1600")
                        }
                    } else if (strUpOrDown == "-") {
                        if ((Radio.Station - paso) >= 88.1) {
                            Radio.ChangeStation(frecActual, paso, "-")
                            println("La estación actual es: ${Radio.Station}")
                        } else {
                            println("La Frecuencia FM tiene una banda de alcance mínimo de 540")
                        }
                    }
                }
            }
            else if (frecActual=="FM"){

                println("Ingrese el signo ´+´ o ´-´ para subir o bajar de estación")
                val strUpOrDown: String? = readLine()
                if (strUpOrDown == "+") {
                    if ((Radio.Station + 10) <= 1600){

                        Radio.ChangeStation(frecActual, 0.0, "+")
                        println("La estación actual es: ${Radio.Station}")
                    }
                    else{
                        println("La Frecuencia FM tiene una banda de alcance máximo de 1600")
                    }
                }
                else if (strUpOrDown == "-") {
                    if ((Radio.Station - 10) >= 540){
                        Radio.ChangeStation(frecActual, 0.0, "-")
                        println("La estación actual es: ${Radio.Station}")
                    }
                    else{
                        println("La Frecuencia FM tiene una banda de alcance mínimo de 540")
                    }
                }
            }


        }
        else if (seleccion==4){
            println("El volúmen actual del Radio es: ${Radio.Volume}")
            println("Ingrese el signo ´+´ o ´-´ para subir o bajar el volúmen")
            val strvolumen: String? = readLine()
            if (strvolumen != null) {
                if (strvolumen=="+"){
                    Radio.ChangeVolume("+")
                    println("El volúmen actual del Radio es: ${Radio.Volume}")

                }
                else if (strvolumen=="-"){
                    Radio.ChangeVolume("-")
                    println("El volúmen actual del Radio es: ${Radio.Volume}")
                }
            }


        }
        else if (seleccion==5){
            if (Radio.isTurnedOn){
                println("Radio Apagado")
                Radio.isTurnedOn=false
            }
            else{
                println("El radio ya está apagado")
            }
        }







    } while ( seleccion !=6

            )


    }

