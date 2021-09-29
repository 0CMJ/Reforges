package com.willfp.reforges.util

import com.willfp.eco.core.EcoPlugin
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe

class DiscoverRecipeListener(
    private val plugin: EcoPlugin
) : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        if (plugin.configYml.getBool("discover-recipes")) {
            Bukkit.getServer().recipeIterator().forEachRemaining { recipe: Recipe ->
                if (recipe is ShapedRecipe) {
                    val key = recipe.key
                    if (key.namespace == "reforges") {
                        if (!key.key.contains("displayed")) {
                            player.discoverRecipe(key)
                        }
                    }
                }
            }
        }
    }
}