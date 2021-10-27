package com.thusee.profile.views.editprofile.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AutoCompleteTextView
import com.bumptech.glide.Glide
import com.thusee.profile.R
import com.thusee.profile.data.response.Data
import com.thusee.profile.data.response.KeyValue
import com.thusee.profile.databinding.ActivityEditProfileBinding
import com.thusee.profile.util.AppUtils.getArrayAdapter
import com.thusee.profile.util.ETHNICITY
import com.thusee.profile.util.FIGURE
import com.thusee.profile.util.GENDER
import com.thusee.profile.util.MARITAL_STATUS
import com.thusee.profile.util.RELIGION
import com.thusee.profile.views.editprofile.EditProfileView
import kotlinx.android.synthetic.main.activity_edit_profile.view.*

class EditProfileViewImpl: EditProfileView {

    lateinit var binding: ActivityEditProfileBinding

    override fun inflate(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
        binding = ActivityEditProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun changeState(state: EditProfileView.State) {
        when (state) {
            is EditProfileView.State.DisplayMultiChoiceData -> updateMultiChoiceUI(state.data)
        }
    }

    override fun updateUI(data: Data) {
        Glide.with(binding.root.context)
            .load(data.profilePic)
            .fitCenter()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(binding.editProfilePicture)

        binding.editDisplayNameText.setText(data.displayName)
        binding.editRealNameText.setText(data.realName)
        binding.editGenderText.setText(data.gender)
        binding.editEthnicityText.setText(data.ethnicity)
        binding.editReligionText.setText(data.religion)
        binding.editHeightText.setText("${data.height}")
        binding.editFigureText.setText(data.figure)
        binding.editMaritalStatusText.setText(data.maritalStatus)
        binding.editOccupationText.setText(data.occuapion)
        binding.editAboutMeText.setText(data.aboutMe)
        binding.editLocationText.setText(data.location?.city)
    }

    private fun updateMultiChoiceUI(result: MutableMap<String, List<KeyValue>>) {

        (binding.editEthnicity.editEthnicityText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, ETHNICITY)
        )
        (binding.editGender.editGenderText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, GENDER)
        )
        (binding.editFigure.editFigureText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, FIGURE)
        )
        (binding.editMaritalStatus.editMaritalStatusText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, MARITAL_STATUS)
        )
        (binding.editReligion.editReligionText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, RELIGION)
        )
    }

}