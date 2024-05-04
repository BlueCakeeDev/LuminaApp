package com.example.lumina.ui.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.lumina.databinding.FragmentArticuloBinding
import com.example.lumina.models.MovieRepository

class ArticuloFragment : Fragment() {

    private var _binding: FragmentArticuloBinding? = null
    private val binding get() = _binding!!

    private val args: ArticuloFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticuloBinding.inflate(inflater, container, false)

        val root: View = binding.root

        // Obtener la película del repositorio usando el ID proporcionado
        val movie = MovieRepository.getMovies().find { it.id == args.id }

        // Verificar si se encontró la película
        if (movie != null) {
            // Asignar los datos de la película a los elementos del XML
            binding.imageView.setImageResource(movie.imageResId)
            binding.textViewTitulo.text = movie.title
            binding.textViewDescripcion.text = movie.description

            // Establecer el evento OnRatingBarChangeListener
            binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                // Mostrar el mensaje cuando se cambia la puntuación
                Toast.makeText(requireContext(), "Gracias por tu calificación", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
