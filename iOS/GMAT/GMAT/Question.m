//
//  Question.m
//  GMAT
//
//  Created by Trung Đức on 3/12/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "Question.h"

@implementation Question

- (instancetype)initWithJson:(NSDictionary *)json;
{
    self = [super init];
    
    if (self) {
        
        _id = json[@"_id"][@"$oid"];
        
        
        NSArray *answers = [NSArray arrayWithArray:json[@"answer_choices"]];
        
        _answerA = answers[0];
        _answerB = answers[1];
        _answerC = answers[2];
        _answerD = answers[3];
        _answerE = answers[4];
        
        _explanation = json[@"explanation"];
        
        _right_answer = json[@"right_answer"];
        
        _stem = json[@"stem"];
        
        _stimulus = json[@"stimulus"];
        
        _subType = json[@"sub_type"];
        
        _type = json[@"type"];
        
    }
    
    return self;
}

@end
