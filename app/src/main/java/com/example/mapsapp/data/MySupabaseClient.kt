package com.example.mapsapp.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class MySupabaseClient() {
    lateinit var client: SupabaseClient
    constructor(supabaseUrl: String, supabaseKey: String): this(){
        client = createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ) {
            install(Postgrest)
        }
    }
    //SQL operations

    //Operacions SELECT
    suspend fun getAllMarkers(): List<Marker> {
        return client.from("Markers").select().decodeList<Marker>()
    }

    suspend fun getMarker(id: String): Marker{
        return client.from("Markers").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<Marker>()
    }

    //Operació INSERT
    suspend fun insertMarker(marker: Marker){
        client.from("Markers").insert(marker)
    }

    //Operació UPDATE
    suspend fun updateMarker(id: String, name: String, description: String, latitude: Double, longitude: Double){
        client.from("Markers").update({
            set("name", name)
            set("description", description)
            set("latitude", latitude)
            set("longitude", longitude)
        }) { filter { eq("id", id) } }
    }

    //Operació DELETE
    suspend fun deleteMarker(id: String){
        client.from("Markers").delete{ filter { eq("id", id) } }
    }

}