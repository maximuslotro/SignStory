<p align="center">
  <a href="https://github.com/maximuslotro/SignStory/releases" target="_blank">
    <img alt="release" src="https://img.shields.io/github/v/release/maximuslotro/SignStory?color=56bcd3" />
  </a>
  <a href="https://github.com/maximuslotro/SignStory/releases" target="_blank">
    <img alt="downloads" src="https://img.shields.io/github/downloads/maximuslotro/SignStory/total?color=56bcd3" />
  </a>
  <a href="https://github.com/maximuslotro/SignStory/main/LICENSE.md" target="_blank">
    <img alt="license" src="https://img.shields.io/badge/License-LGPLv3-blue.svg?style=flat" />
  </a>
</p>

# Sign Story
![Minecraft 1.7.10](https://img.shields.io/badge/Minecraft-1.7.10-green.svg?style=flat)

## Table of Contents

* [About](#about)
* [License](#license)
* [Downloads](#downloads)
* [Installation](#installation)
* [Issues](#issues)
* [Building](#building)
* [Contribution](#contribution)
* [Credits](#credits)

## Downloads

Downloads can be found here on [github](https://github.com/maximuslotr/SignStory/releases).

Downloads can be found here on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/signstory/files).

## About

Ever wanted to place many signs in a row with text being supplied from a txt file? Well look no further. This mod's aim is to allow users to create stories with signs in Minecraft. It also perserves all the functions of the original SignPicture Mod and is like an updated and maintianed version of it.

## Installation

You install this mod by putting it into the `minecraft/mods/` folder. It has no additional hard dependencies.

## Issues

SignStory crashing, have a suggestion, found a bug?  Create an issue now!

1. Make sure your issue has not already been answered or fixed and you are using the latest version. Also think about whether your issue is a valid one before submitting it.
2. Go to [the issues page](https://github.com/maximuslotro/SignStory/issues) and click [new issue](https://github.com/maximuslotro/SignStory/issues/new)
3. Enter your a title of your issue (something that summarizes your issue), and then create a detailed description of the issue.
    * The following details are required. Not including them can cause the issue to be closed.
        * Sign Story version
        * Crash log, when reporting a crash (Please make sure to use [pastebin](http://pastebin.com/))
            * Do not post an excerpt of what you consider important, instead:
            * Post the full log
        * Other mods and their version, when reporting an issue between Sign Story and another mod
            * Also consider updating these before submitting a new issue, it might be already fixed
        * A detailed description of the bug or feature
    * To further help in resolving your issues please try to include the follow if applicable:
        * What was expected?
        * How to reproduce the problem?
            * This is usually a great detail and allows us to fix it way faster
        * Screen shots or Pictures of the problem
5. Click `Submit New Issue`, and wait for feedback!

Providing as many details as possible does help us to find and resolve the issue faster and also you getting a fixed version as fast as possible.

## Building

1. Clone this repository via
  - SSH `git clone git@github.com:maximuslotro/SignStory.git` or
  - HTTPS `git clone https://github.com/maximuslotro/SignStory.git`
2. Setup workspace
  - Decompiled source `gradlew setupDecompWorkspace`
  - Obfuscated source `gradlew setupDevWorkspace`
  - CI server `gradlew setupCIWorkspace`
3. Build `gradlew build`. Jar will be in `build/libs`
4. For core developer: Setup IDE
  - IntelliJ: Import into IDE and execute `gradlew genIntellijRuns` afterwards
  - Eclipse: execute `gradlew eclipse`

## Contribution

Before you want to add major changes, you might want to discuss them with us first, before wasting your time.
If you are still willing to contribute to this project, you can contribute via [Pull-Request](https://help.github.com/articles/creating-a-pull-request).

Here are a few things to keep in mind that will help get your PR approved.

* A PR should be focused on content.
* Use the file you are editing as a style guide.
* Consider your feature.
  - Is your suggestion already possible using Vanilla + Sign Story?
  - Make sure your feature isn't already in the works, or hasn't been rejected previously.
  - Does your feature simplify another feature of Sing Sign? These changes will not be accepted.
  - If your feature can be done by any popular mod, discuss with us first.

Getting Started

1. Fork this repository
2. Clone the fork via
  * SSH `git clone git@github.com:<your username>/SignStory.git` or
  * HTTPS `git clone https://github.com/<your username>/SignStory.git`
3. Change code base
4. Add changes to git `git add -A`
5. Commit changes to your clone `git commit -m "<summery of made changes>"`
6. Push to your fork `git push`
7. Create a Pull-Request on GitHub
8. Wait for review
9. Squash commits for cleaner history

If you are only doing single file pull requests, GitHub supports using a quick way without the need of cloning your fork. Also read up about [synching](https://help.github.com/articles/syncing-a-fork) if you plan to contribute on regular basis.

## License
* Sign Story
  - (c) 2022 maximuslotro
  - [![License](https://img.shields.io/badge/License-LGPLv3-blue.svg?style=flat)](https://raw.githubusercontent.com/maximuslotro/SignStory/main/LICENSE.md)
* Sign Picture
  - (c) 2016 TeamFruit
  - [![License](https://img.shields.io/badge/License-LGPLv3-blue.svg?style=flat)](https://raw.githubusercontent.com/Team-Fruit/SignPicture/master/LICENSE.md)
* Banana Widget
  - (c) 2016 TeamFruit
  - [![License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://opensource.org/licenses/mit-license.php)
* Textures and Models
  - (c) 2016 TeamFruit
  - [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-yellow.svg?style=flat)](https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode)
  - (c) 2007-2017 ppy Pty Ltd
    - [GitHub](https://github.com/ppy/osu-resources)
    - [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-yellow.svg?style=flat)](https://github.com/ppy/osu-resources/blob/master/LICENCE.md)
* Text and Translations
  - [![License](https://img.shields.io/badge/License-No%20Restriction-green.svg?style=flat)](https://creativecommons.org/publicdomain/zero/1.0/)

## Credits

Thanks to

* Notch et al for Minecraft
* Lex et al for MinecraftForge
* Kamesuta, sjcl for [SignPicture](https://github.com/Team-Fruit/SignPicture)
* Kamesuta, sjcl for [BnnWidget](https://github.com/Team-Fruit/BnnWidget/tree/351ee3e321502b7c51e60bdbbdb1a523299a78f1)
* all [contributors](https://github.com/maximuslotro/SignStory/graphs/contributors)
