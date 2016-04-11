//
//  Answer.m
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "Answer.h"
#import "Question.h"
#import "MagicalRecord.h"

@implementation Answer

// Insert code here to add functionality to your managed object subclass

+ (instancetype)createAnswerWithJson:(NSDictionary *)jsonDict andQuestion:(Question *)question;
{
    Answer *newAnswer = [Answer MR_createEntity];

    newAnswer.question = question;
    newAnswer.index = [NSNumber numberWithInteger:[jsonDict[@"index"] integerValue]];
    newAnswer.choice = jsonDict[@"choice"];
    newAnswer.explanation = jsonDict[@"explanation"];
    newAnswer.note = jsonDict[@"note"];
    
    return newAnswer;
}

@end
