# Colored Names #
This plugin gives each player's name a color based on the hashcode of the name.

## Usage ##
To color a player's name, give the player the colorednames.color permission

## Configuration ##
ColoredNames is configurable in the config.yml file in its data folder (plugins/ColoredNames/config.yml). The configuration file should look like:
```yaml
unused-colors:
- DARK_GRAY
- BLACK
```

### Settings overview ###
```unused-colors```: Colors listed in this setting will not be used when selecting player name colors. The default values in this list are here because names in those colors are difficult to read in the Minecraft client.
