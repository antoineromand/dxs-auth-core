# Changelog

## [1.1.0](https://github.com/antoineromand/dxs-auth-core/compare/v1.0.0...v1.1.0) (2025-04-08)


### Features

* Add Jwt lib and spring core, start implementing login usecase. ([c3c5c49](https://github.com/antoineromand/dxs-auth-core/commit/c3c5c49c995428cc89f894af1f6b1109296abd2e))
* add verify token usecase + tests. ([40e0324](https://github.com/antoineromand/dxs-auth-core/commit/40e0324fe54dad7b2d7266aca8b8d47c8c7afcc6))
* change trigger (on push main) and use google release action ([32b0352](https://github.com/antoineromand/dxs-auth-core/commit/32b0352d952b9e980776653e31427e1faf62d6ea))
* Create release github actions (trigger when tag is create) ([28eb60f](https://github.com/antoineromand/dxs-auth-core/commit/28eb60f90df58f352e90088c3eb70251211da081))
* Implement Register Usecase, remove unused exceptions. ([c4a00bf](https://github.com/antoineromand/dxs-auth-core/commit/c4a00bf448af72f948af3eb652b0c5ec29782f1e))
* Implement TokenManager, generate jwt in login usecase, test usecase and token manager ([e91836f](https://github.com/antoineromand/dxs-auth-core/commit/e91836f25a6e5ffcd2251a61c86ef0cd1fdeb3df))
* init lib with gradle ([d23ece5](https://github.com/antoineromand/dxs-auth-core/commit/d23ece526b5228c15d1c2c8928628fba183203bd))
* Upgrade mockito core and add mockito junit, add getter for response class and test RegisterUseCase ([0cec0d4](https://github.com/antoineromand/dxs-auth-core/commit/0cec0d4d314e58cbe33b9293213f51fda43313ff))


### Bug Fixes

* add base name (for .jar), fix issues with password encryption (login usecase + tests affected) ([0c39926](https://github.com/antoineromand/dxs-auth-core/commit/0c39926c8ea123a5637a4c2d49ce978eaaf83668))
* add VERSION file, use googleapis in GA, use extra_files to bump version in VERSION file, get version in build.gradle.kts from VERSION. ([25b14c9](https://github.com/antoineromand/dxs-auth-core/commit/25b14c9b338b6a95816a7bf367ff6c95288fc139))
* change action even (use googleapis), remove package-name (not a valid option) ([479fff6](https://github.com/antoineromand/dxs-auth-core/commit/479fff6d39bd7263c7d40cc35d90ec5c6781bf81))
* execute manually ga ([091151e](https://github.com/antoineromand/dxs-auth-core/commit/091151ef725ca4d94b331179e4c8b2f6d8c18331))
* get in changelog commits from merge. ([4e007d5](https://github.com/antoineromand/dxs-auth-core/commit/4e007d538a1a907e5e0733b5478628629dd79326))
* use git release and a script to generate clean changelog. GA triggers when a tag is pushed. ([5964ace](https://github.com/antoineromand/dxs-auth-core/commit/5964ace347e0640c4f72726c184b8e6bf9c1ffdd))
* use with instead of env in GA release ([2aa4fde](https://github.com/antoineromand/dxs-auth-core/commit/2aa4fde7cbcd267d64772a0d069be0b549a536e7))
