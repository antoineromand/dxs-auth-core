# Changelog

## [1.0.1](https://github.com/antoineromand/dxs-auth-core/compare/v1.0.0...v1.0.1) (2025-04-05)


### Bug Fixes

* fix gitignore and add gradle wrapper ([70828cf](https://github.com/antoineromand/dxs-auth-core/commit/70828cf9ce6be66bf3e14ed9a06d81c1bd0b4a28))
* remove cache from gradle only in ci (compatibilit issue with cache in gradle.properties) and change execution of release action (manual instead of push main). ([85c1771](https://github.com/antoineromand/dxs-auth-core/commit/85c177162d85ab9d876dc2e574a00fa5cc94eca5))

## 1.0.0 (2025-04-05)


### Features

* prepare ci/cd for release and publish on github packages ([5330c81](https://github.com/antoineromand/dxs-auth-core/commit/5330c811bc5246525137cee4d39f8a6342877ad2))


### Bug Fixes

* .kts issues with versionString.match (release-please), use gradle.properties to specify group and version (and exctract) ([19ca465](https://github.com/antoineromand/dxs-auth-core/commit/19ca465509affb2b793eaa4b047c86d268065e2b))
* add base name (for .jar), fix issues with password encryption (login usecase + tests affected) ([216d9be](https://github.com/antoineromand/dxs-auth-core/commit/216d9be325eea0a5c223d3d8f860c01090419ce6))
* add debug steps to check version from build.gradle.kts, use regex in config to target version variable ([034a727](https://github.com/antoineromand/dxs-auth-core/commit/034a7272f7b4c734342f5a691c1bfca27d129b26))
* add manifest to root (for release-please), remove with-file from release.yml ([696dc2e](https://github.com/antoineromand/dxs-auth-core/commit/696dc2e0a00bbb59fb3b4e17c7958b6389aedf07))
* change action (deprecated), error while extracting version from build.gradle.kts ([e2ddc77](https://github.com/antoineromand/dxs-auth-core/commit/e2ddc776796f4af57115ced5d4cc1602814e0aa6))
* match release-please-config with manifest, re-add with file to target release-please-config.json ([eda4674](https://github.com/antoineromand/dxs-auth-core/commit/eda4674580af80e51cc58af408c4dbe2b311fd77))
* packages -&gt; lib to . ([a682740](https://github.com/antoineromand/dxs-auth-core/commit/a68274084ff0a1a12fff369f12424e0e8a9b54f6))
* permission to create pr from github ([63bf72c](https://github.com/antoineromand/dxs-auth-core/commit/63bf72c93048b8f797c9e61f8d7a322d95944717))
* remove configs (manifest is still asked, use workflow directly) ([be16bd1](https://github.com/antoineromand/dxs-auth-core/commit/be16bd1f25c9a1802554165c6112debcc6798774))
* remove manifest (switch from manifest mod to mono-project), change debug in release.yml ([3925068](https://github.com/antoineromand/dxs-auth-core/commit/39250683684c96efe1d16c2e5f5c45cb627a994b))
* try to switch place for group and version in build.gradle.kts ([6386663](https://github.com/antoineromand/dxs-auth-core/commit/638666312f1b1b4061b5e8d5a5967b57c910bf9a))
