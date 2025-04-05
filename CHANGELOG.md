# Changelog

## [1.0.1](https://github.com/antoineromand/dxs-auth-core/compare/v1.0.0...v1.0.1) (2025-04-05)


### Bug Fixes

* auto to manual ([5754059](https://github.com/antoineromand/dxs-auth-core/commit/575405921b4b667a3fc555f78f0b76c9bc3b62e1))
* fix gitignore and add gradle wrapper ([b3c499f](https://github.com/antoineromand/dxs-auth-core/commit/b3c499f2c619bafbbe07c21bee74a68139dd2f6a))
* remove cache from gradle only in ci (compatibilit issue with cache in gradle.properties) and change execution of release action (manual instead of push main). ([188e397](https://github.com/antoineromand/dxs-auth-core/commit/188e397e46378c03107e4ce23cbdb8cb68641edd))
* target main to force github to create tag when pr is merged. ([842275f](https://github.com/antoineromand/dxs-auth-core/commit/842275f83b2778b6ff9b879bae81d707323e0191))
* trigger release only when a pr is merged (remove debug step) ([867e602](https://github.com/antoineromand/dxs-auth-core/commit/867e602071ad8f35c4d23e10a229cda1f7aa8bda))
* wrong secret token ([b2831da](https://github.com/antoineromand/dxs-auth-core/commit/b2831da0c4471dcc8ee1a46493b4a6417becfda1))

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
