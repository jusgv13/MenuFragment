package cr.ac.menufragment.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import cr.ac.menufragment.R
import cr.ac.menufragment.entity.Empleado
import java.io.ByteArrayOutputStream


class EmpleadoAdapter (context: Context, empleados : List<Empleado>)
    : ArrayAdapter<Empleado> (context, 0, empleados){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater=
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rowView = inflater.inflate(R.layout.empleado_item, parent, false)

        val img_avatar = rowView.findViewById<ImageView>(R.id.empleado_imagen)
        val nombre = rowView.findViewById<TextView>(R.id.Nombre)
        val puesto = rowView.findViewById<TextView>(R.id.empleado_puesto)

        val empleado = getItem(position)

        nombre.setText(empleado?.nombre)
        puesto.setText(empleado?.puesto)

        if(empleado?.avatar != ""){
            img_avatar.setImageBitmap(empleado?.avatar?.let { decodeImage(it) })
        }else{
            img_avatar.setImageResource(R.drawable.ic_launcher_foreground)
        }

        return rowView
    }

    private fun decodeImage (b64 : String): Bitmap {
        val imageBytes = Base64.decode(b64, 0)
        return  BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}