//
//  Question.h
//  GMAT
//
//  Created by Trung Đức on 3/21/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>

@class Answer;

NS_ASSUME_NONNULL_BEGIN

@interface Question : NSObject

@property (nullable, nonatomic, retain) NSString *questionId;
@property (nullable, nonatomic, retain) NSString *explanation;
@property (nullable, nonatomic, retain) NSNumber *rightAnswerIdx;
@property (nullable, nonatomic, retain) NSString *stem;
@property (nullable, nonatomic, retain) NSString *stimulus;
@property (nullable, nonatomic, retain) NSString *subType;
@property (nullable, nonatomic, retain) NSString *type;
@property (nullable, nonatomic, retain) NSArray *answers;

// Insert code here to declare functionality of your managed object subclass

- (instancetype)initWithJson:(NSDictionary *)json;

@end

NS_ASSUME_NONNULL_END

#import "Question+CoreDataProperties.h"
