QuakeQuestSwitcher is a very simple bare bones super lazy Android app with only one job: to let me switch between different Quake mods in [Dr. Beef's QuakeQuest for the Oculus Quest](https://github.com/DrBeef/QuakeQuest) without needing a PC (after initial setup) and without even needing to take the VR headset off.

Command line options for QuakeQuest are controlled in `/QuakeQuest/commandline.txt`. QuakeQuestSwitcher goes looking in the `/QuakeQuest/commandline/` folder for any files ending with `.txt` to display a menu of the files. You click on which file you want, and QuakeQuestSwitcher will replace `commandline.txt` with that file. The next time you start QuakeQuest, it will load with the command line options in that file, enabling you to play other Quake engine games!

Of course, you need to put the games on the internal storage in your Quest yourself. Then, you'll need to make a `/QuakeQuest/commandline/` folder and place text files containing the appropriate commands there for QuakeQuestSwitcher to choose from. [I've included the ones I'm using](https://github.com/BenMcLean/QuakeQuestSwitcher/tree/master/commandline).

### Games that work on QuakeQuest

|Game|Folder|Command|
|---|---|---|
|Quake|`/QuakeQuest/id1/`|`quake --supersampling 1.3`|
|Quake Mission Pack 1: Scourge of Armagon|`/QuakeQuest/hipnotic/`|`quake -game hipnotic --supersampling 1.3`|
|Quake Mission Pack 2: Dissolution of Eternity|`/QuakeQuest/rogue/`|`quake -game rogue --supersampling 1.3`|
|Quake Mission Pack 3: Abyss of Pandemonium|`/QuakeQuest/aop/`|`quake -game aop --supersampling 1.3`|
|[QUAKE Episode 5: Dimension of the Past](https://archive.org/details/Quake_Episode_5_Dimension_of_the_past)|`/QuakeQuest/dopa/`|`quake -game dopa --supersampling 1.3`|
|[Star Trek Quake](http://www.gamers.org/pub/idgames2/total_conversions/ncc1701d.zip)|`/QuakeQuest/startrek/`|`quake -game startrek +map ncc1701d --supersampling 1.3`|
|[Quake Matrix](https://www.quakewiki.net/archives/qmatrix/)|`/QuakeQuest/matrix/`|`quake -game matrix +map start --supersampling 1.3`|
|[X-Men: The Ravages of Apocalypse](https://www.moddb.com/mods/x-men-ravages-of-apocalypse/downloads/x-men-ravages-of-apocalypse-tc)|`/QuakeQuest/xmen/`|`quake -game xmen --supersampling 1.3`|
|Malice: 23rd Century Ultraconversion for Quake|`/QuakeQuest/MALICE/`|`quake -game MALICE --supersampling 1.3`|
|Shrak for Quake|`/QuakeQuest/SHRAK/`|`quake -game SHRAK --supersampling 1.3`|

Being on this list means the game boots up in QuakeQuest and lets you into the first map without any obvious show-stopping issues. It does not mean the game can be completed in QuakeQuest. Some of these games may expect extra controls to be mapped to the keyboard, which may not be possible on QuakeQuest.

### Games that don't work (yet?)

* Quake Rally won't ever load the correct first map.

* Nehahra is almost working with the command `quake -game nehahra +map nehstart --supersampling 1.3` but has some technical problems.

I'd appreciate any assistance getting these to work! My email is mclean dot ben at gmail
