package com.example.bootcamptodo.data

import com.example.bootcamptodo.data.local.ToDoEntity
import com.example.bootcamptodo.domain.model.ToDo

fun ToDoEntity?.toDomainOrNull(): ToDo? {
    return this?.let {
        ToDo(
            id = it.id,
            title = it.title,
            description = it.description,
            isCompleted = it.isCompleted
        )
    }
}

// flow yerine suspend kulanmak daha mantikli olabilir. burada bir akistan ziyade tek bir deger bekleriz.
// flow kullanimindan kaynakli null hatalarindan da kacilmis olur.

fun ToDo.toEntity(): ToDoEntity {
    return ToDoEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )
}
