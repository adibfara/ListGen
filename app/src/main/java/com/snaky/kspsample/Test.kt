package com.snaky.kspsample

import com.snaky.ksp.annotation.Listed
import com.snaky.ksp.annotation.Mapped

@Listed("mainList")
fun mainModule() = 2

@Listed("mainList")
fun secondModule() = 3

@Listed("otherList")
fun helloModule() = "hello!"

@Mapped("firstMap", "key1")
fun firstMapTest() = "value1"

@Mapped("firstMap", "key2")
fun firstMapTest2() = "value2"

@Mapped("secondMapMap", "first")
fun secondMap() = "hello"