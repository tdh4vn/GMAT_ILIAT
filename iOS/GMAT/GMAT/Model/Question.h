//
//  Question.h
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>

@class Answer;
@class StudentAnswer;

NS_ASSUME_NONNULL_BEGIN

@interface Question : NSManagedObject

// Insert code here to declare functionality of your managed object subclass

+ (instancetype)createQuestionWithJson:(NSDictionary *)jsonDict;

@end

NS_ASSUME_NONNULL_END

#import "Question+CoreDataProperties.h"
