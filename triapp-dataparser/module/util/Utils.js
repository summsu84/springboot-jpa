/**
 * Created by JJW on 2017-07-20.
 */
var _ = require('lodash');
var Utils = {

    isEmpty: function(variable){

        if(_.isUndefined(variable))
            return true;

        if(_.isNull(variable))
            return true;

        if(_.isString(variable) && _.isEmpty(variable))
            return true;

        return false;

    },
    localizeString: function(str){

        return str;
    },
    now: function(){
        return Math.floor(Date.now());
    },
    stripPrivacyParams: function(user){
        delete user.token;
        return user;
    },
    stripPrivacyParamsFromArray: function(users){

        var result = [];
        var self = this;

        _.forEach(users,function(user){

            result.push(self.stripPrivacyParams(user));

        });


        return result;
    }

}

module["exports"] = Utils;