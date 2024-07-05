package com.example.a2024aswgr1vaes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context
):SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearEntrenador(
        nombre: String,
        descripcion:String
    ):Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "ENTRENADOR", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }
    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        // Consulta SQL: where ... ID=? AND NOMBRE=?  [?=1, ?=2]
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt()==-1) false else true
    }
    fun actualizarEntrenadorFormulario(
        nombre:String, descripcion: String, id: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        // where: .....
        val parametrosConsultaActualizar = arrayOf( id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTENADOR",
                valoresAActualizar, // nombre= Adrian, descripcion=B
                "id=?", // id=1
                parametrosConsultaActualizar // [1]
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt()==-1) false else true
    }




















}