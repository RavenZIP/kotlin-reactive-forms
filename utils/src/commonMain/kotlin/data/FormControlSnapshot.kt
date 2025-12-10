package data

data class FormControlSnapshot<T>(
    val value: T,
    val typeChange: ValueChangeType,
    val hasChanges: Boolean,
    val status: FormControlStatus,
    val touched: Boolean,
    val dirty: Boolean,
    val valid: Boolean,
    val invalid: Boolean,
    val enabled: Boolean,
    val disabled: Boolean,
    val errorMessages: List<String>,
) {
    companion object {
        fun <T> create(
            value: T,
            typeChange: ValueChangeType,
            status: FormControlStatus,
            touched: Boolean,
            dirty: Boolean,
            errorMessages: List<String>,
        ) =
            FormControlSnapshot(
                value = value,
                typeChange = typeChange,
                hasChanges = !typeChange.isInitialize(),
                status = status,
                touched = touched,
                dirty = dirty,
                valid = status.isValid(),
                invalid = status.isInvalid(),
                enabled = status.isEnabled(),
                disabled = status.isDisabled(),
                errorMessages = errorMessages,
            )
    }
}
