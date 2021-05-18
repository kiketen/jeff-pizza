package com.jeff.pizza.pageobject

import androidx.annotation.IdRes
import com.jeff.pizza.login.R
import com.jeff.pizza.utils.ConditionWatcher
import com.jeff.pizza.utils.WaitForVisibleViewInstruction
import com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked
import com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertUnchecked
import com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertDisabled
import com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertEnabled
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaRadioButtonInteractions.clickRadioButtonItem


class UserTypePageObject: PageObject {

    fun assertUserTypeNotSelected() {
        assertUnchecked(R.id.singleOptionLogin)
        assertUnchecked(R.id.marriedOptionLogin)
        assertDisabled(R.id.confirmButtonLogin)
    }

    fun assertUserTypeSelected(@IdRes typeOptionId: Int) {
        assertChecked(typeOptionId)
        assertEnabled(R.id.confirmButtonLogin)
    }

    fun assertLoginError() {
        assertTextVisible(R.string.login_error)
    }

    fun onUserTypeClick(@IdRes typeOptionId: Int) {
        clickRadioButtonItem(R.id.userTypeOptionsLogin, typeOptionId)
    }

    fun onConfirmButtonClick() {
        clickOn(R.id.confirmButtonLogin)
    }
}