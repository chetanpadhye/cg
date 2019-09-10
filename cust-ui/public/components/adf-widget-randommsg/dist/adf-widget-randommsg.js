(function (window, undefined) {
  'use strict';
  /*
   * The MIT License
   *
   * Copyright (c) 2015, Sebastian Sdorra
   *
   * Permission is hereby granted, free of charge, to any person obtaining a copy
   * of this software and associated documentation files (the "Software"), to deal
   * in the Software without restriction, including without limitation the rights
   * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   * copies of the Software, and to permit persons to whom the Software is
   * furnished to do so, subject to the following conditions:
   *
   * The above copyright notice and this permission notice shall be included in
   * all copies or substantial portions of the Software.
   *
   * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   * SOFTWARE.
   */



  angular.module('adf.widget.randommsg', ['adf.provider'])
    .config(["dashboardProvider", function (dashboardProvider) {
      dashboardProvider
        .widget('randommsg', {
          title: 'Customer Cases',
          description: 'Displays a random quote of Douglas Adams',
          templateUrl: '{widgetsPath}/components/adf-widget-randommsg/dist/view.html',
          controller: 'randommsgCtrl'
        });
    }])
    .service('randommsgService', function () {
      // source http://bookriot.com/2012/05/25/the-42-best-lines-from-douglas-adams-the-hitchhikers-guide-to-the-galaxy-series/
      var msgs = [
        'There 12344 is a theory which states that if ever anyone discovers exactly what the Universe is for and why it is here, it will instantly disappear and be replaced by something even more bizarre and inexplicable. There is another theory which states that this has already happened.',

      ];

      return {
        get: function () {
          return {
            text: msgs[Math.floor(Math.random() * msgs.length)],
            author: 'Douglas Adams'
          };
        }
      };
    })
    .controller('randommsgCtrl', ["$location", "$scope", "randommsgService", function ($location, $scope, randommsgService) {
      $scope.msg = randommsgService.get();

      $scope.table = [{ "name": "Chetan", "link": "link" }, { "name": "Chetan", "link": "link" }, { "name": "Chetan", "link": "link" }, { "name": "Chetan", "link": "link" }, { "name": "Chetan", "link": "link" }];

      $scope.linkClicked = function (x) {
        console.log("DDD" + x);
        $location.url('boards/_1568121222399?case=' + x.name);
      }
    }]);

  angular.module("adf.widget.randommsg").run(["$templateCache", "$http", function ($templateCache, $http) {

    // $http.get("/view.html").then(function (response) {
    //   $templateCache.put('{widgetsPath}/adf-widget-randommsg/dist/view.html', response.data);
    // }, function (errorResponse) {
    //   console.log('Cannot load the file template');
    // })


  }]);
})(window);