# MCInfluxExporter

Simple exporter for Minecraft metrics to InfluxDB:
 * Heap Usage (mc_jeap_usage)
 * Heap Max (mc_heap_max)
 * Heap Usage Percent (mc_heap_percent)
 * Online Players Count (mc_players_count)
 * Living Entities Count (mc_living_entities)


# Configuration file:

It is created by default, but you can create it at first in plugins/MCInfluxExporter/config.xml like:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<PluginConf>
    <DBInfo>
        <Database>mcmetrics</Database>
        <Host>localhost</Host>
        <Port>8086</Port>
        <Username>mcadmin</Username>
        <Password>myPWD</Password>
    </DBInfo>
</PluginConf>

```
# Full configuration

You can follow my blog [here](http://triedge.fr/index.php/2020/07/21/minecraft-monitoring-with-grafana) to see how to display the metrics in Grafana and much more!