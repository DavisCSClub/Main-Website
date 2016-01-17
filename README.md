| Production | Preview | Integration |
| ---------- | ------- | ----------- |
| [![Build Status](https://travis-ci.org/DavisCSClub/Main-Website.svg?branch=production)](https://travis-ci.org/DavisCSClub/Main-Website) | [![Build Status](https://travis-ci.org/DavisCSClub/Main-Website.svg?branch=preview)](https://travis-ci.org/DavisCSClub/Main-Website) | [![Build Status](https://travis-ci.org/DavisCSClub/Main-Website.svg?branch=integration)](https://travis-ci.org/DavisCSClub/Main-Website) |

# IMPORTANT

Because of licenses on a few of our HTML templates, the resources directory has not been included into this repository. The resources directory has been converted into a git submodule with a private repository in Bitbucket. Contact any of the repository owners for access.

## Terminologies
- Root Repository: This is the repository that holds the other repositories as submodules. It is also the root directory of the overall project. This code is held in Github. 
- Resource Repository: This is the repository that holds all the resources. HTML, CSS, JS, properties, etc. It is a submodule of the root repository. It is the `src/main/resources` directory.

## Setup
1. Have a Github account and a Bitbucket account ready with your ssh keys registered to each.
2. Contact one of the webdev team members to have your account added to both the main code repository and the resource repository.
3. Once you have both, perform `git clone --recursive git@github.com:DavisCSClub/Main-Website.git`.
4. Check that the resource directory `<root>/src/main/resources/` was also cloned. We will refer to this as the resource repository and the overall repository as the root repository.
5. Checkout branch `integration` for the root repository and check that it is tracking the remote `integration` branch.
6. The resource repository only has one branch - `production`. Its state is tracked by root repository as a submodule. 

## Running Locally
- `gradlew bootRun` (Compiles and runs the build on `localhost:8080`)

## Committing Changes
1. Commit changes to the resource repository first. Commit messages into the resource repository only need a number sign (#) followed by the github issue number. Example: `#123`
2. After committing changes to resource repository, commit all changes to the root repository. If there were changes to resource repository, one of the changes in root repsitory committed should be a subproject commit `src/main/resources`.
3. Commit messages to the root repository should start with a number sign (#) followed by the github issue number and then a summary of the changes. Example: `#123 Made a change here and there`
