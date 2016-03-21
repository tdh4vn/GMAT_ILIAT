//
//  Question.m
//  GMAT
//
//  Created by Trung Đức on 3/21/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "Question.h"

@implementation Question

// Insert code here to add functionality to your managed object subclass

- (instancetype)initWithJson:(NSDictionary *)json;
{
    self = [super init];
    
    if (self) {
        
        self.questionId = json[@"_id"][@"oid"];
        
        self.explanation = json[@"explanation"];
        
        self.rightAnswerIdx = json[@"right_answer"];
        
        self.stem = json[@"stem"];
        
        self.answers = [[NSArray alloc]init];
        
        self.answers = json[@"answer_choices"];
        
        self.stimulus = json[@"stimulus"];
        
        self.subType = json[@"sub_type"];
        
        self.type = json[@"type"];
        
    }
    
    return self;
}

@end
