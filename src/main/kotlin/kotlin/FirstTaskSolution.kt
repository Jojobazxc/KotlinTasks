package com.example.kotlin

object FirstTaskSolution {

    init {
        val map = HashMap<Key, String>()
        val keyForMap = Key(1, "")
        map[keyForMap] = ""

        println(map.contains(keyForMap)) //true

        keyForMap.changeField2()

        println(map.contains(keyForMap)) //false
        println(map[keyForMap]) // null

        val answer = """
            Да, с таким data классом могут возникнуть проблемы, в случае использования его как ключа для hashMap,
            так как у него в конструкторе объявлен изменяемый параметр field2. Для наглядности я добавил функцию 
            changeField2, которая присвоит значение filed3 для field2. 
            
            Представим, что мы положили в хеш мапу элемент с ключом Key(1, ""), затем в процессе изменили значение
            field2 у нашего ключа. Так как hashCode в дата классе считается на основе полей конструктора, то при 
            попытке достать значение по нашему ключу keyForMap с измененным field2, компилятор пересчитает hashCode
            для нового значения, и попадет уже в другой баккет, в котором уже не будет значения, ключом которого был
            keyForMap до изменения, поэтому мы получим null.
            
            
            Чтобы избежать такой ситуации, мы можем: 
            1. Сделать поля в конструкторе неизменяемыми
            2. Сделать ключом field1, а field2, вместе с field3 сделать значениями
            3. При изменении ключа удалить старую запись и сделать новую с измененным ключом
            
        """.trimIndent()

    }
}

data class Key(
    val field1: Int,
    var field2: String
) {
    var field3: String? = null

    fun changeField2() {
        field2 = field3 ?: "123"
    }

}