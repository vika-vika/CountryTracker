package net.vnnz.apps.kotlin.tracker.pojo


data class ListItem(val item: Item)  {
    val isSelected :Boolean = false
    var isVisited  :Boolean = false

    val name  = item.name
    val key   = item.key
    val color = item.color

}