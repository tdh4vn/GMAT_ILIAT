//
//  Question+CoreDataProperties.m
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

#import "Question+CoreDataProperties.h"

@implementation Question (CoreDataProperties)

@dynamic explanation;
@dynamic questionId;
@dynamic rightAnswerIdx;
@dynamic stem;
@dynamic stimulus;
@dynamic subType;
@dynamic type;
@dynamic tag;
@dynamic answers;
@dynamic studentAnswer;

@end
