| Production | Preview | Integration |
| ---------- | ------- | ----------- |
| [![Build Status](https://travis-ci.org/DavisCSClub/Main-Website.svg?branch=production)](https://travis-ci.org/DavisCSClub/Main-Website) | [![Build Status](https://travis-ci.org/DavisCSClub/Main-Website.svg?branch=preview)](https://travis-ci.org/DavisCSClub/Main-Website) | [![Build Status](https://travis-ci.org/DavisCSClub/Main-Website.svg?branch=integration)](https://travis-ci.org/DavisCSClub/Main-Website) |

# IMPORTANT

Because of licenses on a few of our HTML templates, the resources directory has not been included into this repository. The resources directory has been converted into a git submodule with a private repository in Bitbucket. Contact any of the repository owners for access.

## How to contribute
### Features and Bug Fixes
1. Create a branch off of `production` with an appropriate title related to the task you are going to do
  * in case you need a reminder, start on `production` branch:  
```
    $ git pull
    $ git checkout -b name_your_branch
```
2. Do the task (have fun while doing it!)
3. Don't be afraid to ask questions. Reach out to any of the contributors.
4. When you are done, **make sure to `git fetch && git rebase origin/production` on your branch before next step** (to ensure that we keep a clean git history) and fix any conflicts that may arise
5. Run `git push origin HEAD` and create a Pull Request in Github - someone will review your code and suggest changes as necessary.
6. Relax, you just helped us become a little better :)

## How to run on local
We're using Gradle as our build script. The commands are as follows:

- `gradle build` (It's like `make` - compiles and tests the system)
- `gradle test` (Run all tests)
- `gradle bootRun` (boots the war file up and runs it on localhost:8080)

### To use with your favorite IDEs
- `gradle eclipse` (creates all eclipse config files so you can import it)
- `gradle idea` (creates all idea config files so you can import it)
