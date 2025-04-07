## 🚀 Features
- 🚀 feat: create release-pr and release-publish

## 🐛 Fixes
- 🐛 fix: commit changelog before create pr.
- 🐛 fix: remove Set up Git step's in release-pr (issue and not required anymore - fetch-depth:0 )
- 🐛 fix: Add concurrency for release-pr, rename core to chore (conventionnal)
- 🐛 fix: add token to release-publish, use temporary branch to fix git code 128 issue.
- 🐛 fix: publish manually (no more triggered by release), correct publishing part in build.gradle.kts
- 🐛 fix: correct env name for credentials in build.gradle.kts
- 🐛 fix: wrong secret token
- 🐛 fix: auto to manual
- 🐛 fix: trigger release only when a pr is merged (remove debug step)
- 🐛 fix: target main to force github to create tag when pr is merged.
- 🐛 fix: remove cache from gradle only in ci (compatibilit issue with cache in gradle.properties) and change execution of release action (manual instead of push main).
- 🐛 fix: fix gitignore and add gradle wrapper
- 🐛 fix: permission to create pr from github
- 🐛 fix: remove configs (manifest is still asked, use workflow directly)
- 🐛 fix: remove manifest (switch from manifest mod to mono-project), change debug in release.yml
- 🐛 fix: packages -> lib to .
- 🐛 fix: .kts issues with versionString.match (release-please), use gradle.properties to specify group and version (and exctract)
- 🐛 fix: add debug steps to check version from build.gradle.kts, use regex in config to target version variable
- 🐛 fix: try to switch place for group and version in build.gradle.kts
- 🐛 fix: change action (deprecated), error while extracting version from build.gradle.kts
- 🐛 fix: match release-please-config with manifest, re-add with file to target release-please-config.json
- 🐛 fix: add manifest to root (for release-please), remove with-file from release.yml

## ⚙️ Chore
- ⚙️ chore: bump version to v1.1.0
- ⚙️ chore(main): release 1.1.0
- ⚙️ chore(main): release 1.0.1
- ⚙️ chore(main): release 1.0.0

## 🔸 Non classé
- 🔸 Merge branch 'feature/ci-cd' into develop
- 🔸 Merge branch 'feature/ci-cd' into develop
- 🔸 PR Test - CI/CD
- 🔸 PR - Release 1.0.1
- 🔸 Merge pull request #10 from antoineromand/fix/release
- 🔸 PR from fix/release-and-publish branch
- 🔸 Release 1.0.0
