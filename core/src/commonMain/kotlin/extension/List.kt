package extension

internal fun <T, K> List<T>.addOrRemove(value: T, keySelector: (T) -> K): MutableList<T> {
    val current = this.toMutableList()
    val valueKey = keySelector(value)
    val existingIndex = current.indexOfFirst { keySelector(it) == valueKey }
    val isExists = existingIndex >= 0

    if (isExists) current.removeAt(existingIndex) else current.add(value)
    return current
}
