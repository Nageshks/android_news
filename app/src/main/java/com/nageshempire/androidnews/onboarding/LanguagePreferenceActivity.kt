package com.nageshempire.androidnews.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nageshempire.androidnews.MainActivity
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.ActivityLanguagePreferenceBinding
import com.nageshempire.androidnews.language
import com.nageshempire.androidnews.languageUnchecked
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguagePreferenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguagePreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguagePreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val languages = resources.getStringArray(R.array.languages).mapIndexed { i, it ->
            if (i == 0) Language(it, true) else Language(it, false)
        }
        binding.list.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        setupLanguages(languages)
        binding.button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setupLanguages(languages: List<Language>) {
        binding.list.withModels {
            languages.forEach {
                if(it.checked){
                    language {
                        id(it.id)
                        title(it.name)
                        clickListener { _ ->
                            // not required to do anything
                        }
                    }
                }else {
                    languageUnchecked {
                        id(it.id)
                        title(it.name)
                        clickListener { _ ->
                            checkLanguage(it,languages)
                        }
                    }
                }
            }
        }
    }

    private fun checkLanguage(languageClicked: Language,languages: List<Language>){
        val newLanguages = languages.map {
            if(it.id == languageClicked.id) it.copy(checked = true)else it.copy(checked = false)
        }
        setupLanguages(newLanguages)
    }
}