package com.example.mapsapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import com.example.mapsapp.BuildConfig
import com.example.mapsapp.SupabaseApplication.Companion.supabase
import com.example.mapsapp.utils.AuthState
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MySupabaseClient(private val marcador : SupabaseClient) {
    private var storage: Storage= marcador.storage

    private val supabaseUrl = BuildConfig.SUPABASE_URL
    private val supabaseKey = BuildConfig.SUPABASE_KEY

    //SQL operations
    //Operacions SELECT
    suspend fun getAllMarkers(): List<Marker> {
        return marcador.from("Markers").select().decodeList<Marker>()
    }

    suspend fun getMarker(id: String): Marker{
        return marcador.from("Markers").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<Marker>()
    }

    //Operació INSERT
    suspend fun insertMarker(name: String, description: String, latitude: Double, longitude: Double, imageName: String){
        val marker = Marker(name = name, description = description, latitude = latitude, longitude = longitude, image = imageName)
        marcador.from("Markers").insert(marker)
    }

    //Operació UPDATE
    suspend fun updateMarker(id: String, name: String, description: String, latitude: Double, longitude: Double, imageName: String, imageFile: ByteArray){
        val imageName = storage.from("images").update(path = imageName, data = imageFile)
        marcador.from("Markers").update({
            set("name", name)
            set("description", description)
            set("latitude", latitude)
            set("longitude", longitude)
            set("image", buildImageUrl(imageFileName = imageName.path))
        }) { filter { eq("id", id) } }
    }

    //Operació DELETE
    suspend fun deleteMarker(id: String){
        marcador.from("Markers").delete{ filter { eq("id", id) } }
    }

    //Pujar imatge amb storage
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun uploadImage(imageFile: ByteArray): String {
        val fechaHoraActual = LocalDateTime.now()
        val formato = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
        val imageName = storage.from("images").upload(path = "image_${fechaHoraActual.format(formato)}.png", data = imageFile)
        return buildImageUrl(imageFileName = imageName.path)
    }

    fun buildImageUrl(imageFileName: String) = "${this.supabaseUrl}/storage/v1/object/public/images/${imageFileName}"

    //Eliminar una imatge
    suspend fun deleteImage(imageName: String){
        val imgName = imageName.removePrefix("https://aobflzinjcljzqpxpcxs.supabase.co/storage/v1/object/public/images/")
        marcador.storage.from("images").delete(imgName)
    }
}