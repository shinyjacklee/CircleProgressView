# CircleProgressView
A lite Circle Progress View with dot and dash effects, just .java file,no lib.
(BTW,it's a eclipse project...)


![](https://github.com/jackL-e-e/CircleProgressView/blob/master/CircleProgressView/screenShot.gif)

#CircleProgressView in my eyes
Recently i released one CircleProgressView with dot and dash effect in my github . Here i wanna make some explain about this view.

There are two ways to make it looks like circleProgressView.

1. Just draw three round oval with different radius(strictly the one between top and bottom layer is a filled ARC,100% then it’s a round oval…),so we just draw the arc ,its degree indicates the progress.

2. The second way to make it a CircleProgressView is draw two roundel(not filled) ,one as background and another arc(not filled) as progress.

And i try this two both ,i.e one with dot another with dash effect.

Well,at this time you realize this CircleProgress is just a trick of arc! yes exactly!

Simple as it is ? yes ,go to see the source code to explore more : )

#License
```
Copyright 2015 jackLee

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

