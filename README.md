# Create: Simple Archeology

Create: Simple Archeology adds bulk fan processing for ageing materials and brushing suspicious blocks.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.256 or newer in the 21.1 line
- Create 6.0.9 for Minecraft 1.21.1
- JEI is Supported.

## Using the Catalysts

Craft a **Bulk Ageing Catalyst** or **Bulk Brushing Catalyst**, then place the matching block in the Encased Fan's processing airflow. The catalyst selects which recipe type the fan performs. The Brushing Catalyst can face toward you when placed.

## Recipes

| Processing | Input | Output | Config option |
| --- | --- | --- | --- |
| Ageing | Any item in `#minecraft:meat` | Rotten Flesh | `allowAgeingMeat` |
| Ageing | Sand | Suspicious Sand | `allowAgeingSand` |
| Ageing | Gravel | Suspicious Gravel | `allowAgeingGravel` |
| Brushing | Suspicious Sand | One weighted loot result | Always enabled |
| Brushing | Suspicious Gravel | One weighted loot result | Always enabled |

Each suspicious block produces exactly one item when brushed. Its loot pool combines three source loot distributions, giving each source table one third of the total weight. Duplicate items are combined into a single result entry. The sand pool has 22 possible results; the gravel pool has 48.
