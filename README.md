# Balloon

[![Bintray][bintraybadge-svg]][bintray]

## How to use

```java
Balloon.make("My balloon text")
    .icon(BalloonIcon.info())
    .duration(5000)
    .action("Toast", () -> {
        Toast.makeText(context, "My toast message", Toast.LENGTH_SHORT).show();
    })
    .show();
```

<img src="balloon_sample.png" width="600">

## How to integrate

```groovy
implementation 'com.acelost.balloon:balloon:0.0.3'
```

## License

    Copyright 2019 The Spectrum Author

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[bintray]: https://bintray.com/acelost/Balloon/balloon
[bintraybadge-svg]: https://img.shields.io/bintray/v/acelost/Balloon/balloon.svg