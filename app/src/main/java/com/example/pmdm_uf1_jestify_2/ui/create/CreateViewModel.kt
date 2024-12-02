package com.example.pmdm_uf1_jestify_2.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin euismod, libero nec venenatis tincidunt, lorem nisi posuere lacus, in fermentum eros arcu non nulla. Fusce ut erat id erat volutpat elementum. Suspendisse accumsan nisl ac odio cursus, id fermentum mi tristique. Cras dapibus ligula eget eros tempus venenatis. Integer aliquet felis ut justo sodales, ac volutpat enim viverra. Ut eu nisi augue. Phasellus et erat ac eros dignissim tincidunt."
    }

    // use .luach{  } to do internet petitions on a secondary thread.

    val text: LiveData<String> = _text
}