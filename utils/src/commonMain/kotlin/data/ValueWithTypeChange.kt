package data

data class ValueWithTypeChange<T>(val value: T, val typeChange: ValueChangeType) {
    companion object {
        fun <T> create(value: T, typeChange: ValueChangeType): ValueWithTypeChange<T> =
            ValueWithTypeChange(value, typeChange)

        fun <T> createInitializeChange(value: T): ValueWithTypeChange<T> =
            ValueWithTypeChange(value, ValueChangeType.Initialize)

        fun <T> createSetChange(value: T): ValueWithTypeChange<T> =
            ValueWithTypeChange(value, ValueChangeType.Set)

        fun <T> createResetChange(value: T): ValueWithTypeChange<T> =
            ValueWithTypeChange(value, ValueChangeType.Reset)
    }
}
