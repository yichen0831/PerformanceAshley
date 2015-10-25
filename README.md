# Performance test with [LibGdx Ashley](https://github.com/libgdx/ashley) Entity System

Simply create 600 entities per second, and toggle the entity life of 10 seconds by press Q key.

Result shows the performance drops dramatically after 60,000 entities are created. The removal of entities also takes heavy load.

Performace is significantly worse than [Artemis-odb](https://github.com/junkdog/artemis-odb). Refering [test](https://github.com/yichen0831/PerformanceArtemis-odb).


![https://github.com/yichen0831/PerformanceAshley/raw/master/screenshot.png](https://github.com/yichen0831/PerformanceAshley/raw/master/screenshot.png)
